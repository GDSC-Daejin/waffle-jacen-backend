import express from 'express';
import { ExpressRouter } from '../utils/expressRouter';
import {
  CreateTodoRequest,
  DeleteTodoRequest,
  ReadTodoRequest,
  RecoverTodoRequest,
  TodoListParams,
  TodoListRequest,
  TodoTrashListParams,
  TodoTrashListRequest,
  UpdateTodoRequest,
} from '../models/request/todo';
import {
  CreateTodoResponse,
  DeleteTodoResponse,
  ReadTodoResponse,
  RecoverTodoResponse,
  TodoListResponse,
  TodoTrashListResponse,
  UpdateTodoResponse,
} from '../models/response/todo';
import { pagingResult } from '../models/response';
import { parseIntOrDefault } from '../utils/number';
import { splitOrDefault } from '../utils/string';
import db from '../db';

const todoRouter = express.Router();
const myRouter = new ExpressRouter(todoRouter);

myRouter
  .route('/trash')
  .get<TodoTrashListResponse, TodoTrashListRequest, TodoTrashListParams>(
    async (req, res) => {
      const {
        page: pageParam,
        size: sizeParam,
        sort: sortParam,
      } = req.query ?? {};
      const page = parseIntOrDefault(pageParam, 0);
      const size = parseIntOrDefault(sizeParam, 10);
      const sort = splitOrDefault(sortParam, ',', ['desc', 'id']);

      const [totalCount, result] = await Promise.all([
        db.todo.countDocuments(),
        db.todo
          .find({
            isDeleted: true,
          })
          .sort(sort)
          .limit(size)
          .skip(page * size)
          .toArray(),
      ]);

      res.send({
        success: true,
        data: {
          todos: result,
          paging: pagingResult(totalCount, page, size),
        },
      });
    }
  );

myRouter
  .route('/recover/:id')
  .put<RecoverTodoResponse, RecoverTodoRequest>(async (req, res) => {
    const id = req.params.id;
    const result = await db.todo.findOneAndUpdate(
      {
        id,
        isDeleted: true,
      },
      {
        $set: {
          isDeleted: false,
        },
      }
    );

    if (result.ok === 0 || result.value === null) {
      res.send({
        success: false,
        data: '휴지통에 일치하는 id를 가진 todo가 없습니다.',
      });
      return;
    }
    res.send({
      success: true,
      data: result.value,
    });
  });

myRouter
  .route('/:id')
  .post<CreateTodoResponse, CreateTodoRequest>(async (req, res) => {
    if (!req.body) {
      res.status(400).send({
        success: false,
        data: 'request body가 필요합니다.',
      });
      return;
    }

    const { title, content = '' } = req.body;
    const id = req.params.id;
    const result = await db.todo.findOneAndUpdate(
      {
        id,
        isDeleted: false,
      },
      {
        $set: {
          title,
          content,
        },
      }
    );

    console.log(result);
    if (result.ok === 0 || result.value === null) {
      res.send({
        success: false,
        data: '일치하는 id를 가진 todo가 없습니다.',
      });
      return;
    }
    res.send({
      success: true,
      data: result.value,
    });
  })
  .get<ReadTodoResponse, ReadTodoRequest>(async (req, res) => {
    const id = req.params.id;
    const result = await db.todo.findOne({
      id,
      isDeleted: false,
    });

    if (result === null) {
      res.send({
        success: false,
        data: '일치하는 id를 가진 todo가 없습니다.',
      });
      return;
    }
    res.send({
      success: true,
      data: result,
    });
  })
  .put<UpdateTodoResponse, UpdateTodoRequest>(async (req, res) => {
    if (!req.body) {
      res.status(400).send({
        success: false,
        data: 'request body가 필요합니다.',
      });
      return;
    }

    const { title, content = '', completed } = req.body;
    const id = req.params.id;
    const result = await db.todo.findOneAndUpdate(
      {
        id,
        isDeleted: false,
      },
      {
        $set: {
          title,
          content,
          completed,
        },
      }
    );

    console.log(result);
    if (result.ok === 0 || result.value === null) {
      res.send({
        success: false,
        data: '일치하는 id를 가진 todo가 없습니다.',
      });
      return;
    }
    res.send({
      success: true,
      data: result.value,
    });
  })
  .delete<DeleteTodoResponse, DeleteTodoRequest>(async (req, res) => {
    const id = req.params.id;
    const todoItem = await db.todo.findOne({ id });
    if (todoItem === null) {
      res.send({
        success: false,
        data: '일치하는 id를 가진 todo가 없습니다.',
      });
      return;
    }

    if (todoItem.isDeleted === true) {
      const result = await db.todo.findOneAndDelete({
        id,
      });
      if (result.ok === 0 || result.value === null) {
        res.send({
          success: false,
          data: '오류가 발생했습니다.',
        });
        return;
      }
      res.send({
        success: true,
        data: result.value,
      });
    }
    const result = await db.todo.findOneAndDelete({
      id,
    });
    if (result.ok === 0 || result.value === null) {
      res.send({
        success: false,
        data: '오류가 발생했습니다.',
      });
      return;
    }
    res.send({
      success: true,
      data: result.value,
    });
  });

myRouter
  .route('/')
  .get<TodoListResponse, TodoListRequest, TodoListParams>(async (req, res) => {
    const {
      page: pageParam,
      size: sizeParam,
      sort: sortParam,
    } = req.query ?? {};
    const page = parseIntOrDefault(pageParam, 0);
    const size = parseIntOrDefault(sizeParam, 10);
    const sort = splitOrDefault(sortParam, ',', ['desc', 'id']);

    const [totalCount, result] = await Promise.all([
      db.todo.countDocuments(),
      db.todo
        .find()
        .sort(sort)
        .limit(size)
        .skip(page * size)
        .toArray(),
    ]);

    res.send({
      success: true,
      data: {
        todos: result,
        paging: pagingResult(totalCount, page, size),
      },
    });
  });

export default todoRouter;
