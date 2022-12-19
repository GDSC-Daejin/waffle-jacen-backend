import express from "express";
import { ExpressRouter } from "../utils";
import { CreateTodoRequest, DeleteTodoRequest, ReadTodoRequest, RecoverTodoRequest, TodoListParams, TodoListRequest, TodoTrashListParams, TodoTrashListRequest, UpdateTodoRequest } from "../models/request/todo";
import { CreateTodoResponse, DeleteTodoResponse, ReadTodoResponse, RecoverTodoResponse, TodoListResponse, TodoTrashListResponse, UpdateTodoResponse } from "../models/response/todo";

const todoRouter = express.Router();
const myRouter = new ExpressRouter(todoRouter);

myRouter.route('/trash')
	.get<TodoTrashListResponse, TodoTrashListRequest, TodoTrashListParams>((req, res) => {
		res.send({
			success: true,
			data: {
				todos: [
					{
						id: 'string',
						title: 'string',
						content: 'string',
						completed: true,
						createdDate: new Date(),
						updatedDate: new Date(),
						isDeleted: true,
					}
				],
				paging: {
					total_pages: 1,
					current_page: 1,
					is_last_page: true,
				}
			}
		});
	})

myRouter.route('/recover')
	.put<RecoverTodoResponse, RecoverTodoRequest>((req, res) => {
		res.send({
			success: true,
			data: {
				id: 'string',
				title: 'string',
				content: 'string',
				completed: true,
				createdDate: new Date(),
				updatedDate: new Date(),
				isDeleted: true,
			}
		});
	});

myRouter.route('/:id')
	.post<CreateTodoResponse, CreateTodoRequest>((req, res) => {
		res.send({
			success: true,
			data: {
				id: 'string',
				title: 'string',
				content: 'string',
				completed: true,
				createdDate: new Date(),
				updatedDate: new Date(),
				isDeleted: true,
			}
		});
	})
	.get<ReadTodoResponse, ReadTodoRequest>((req, res) => {
		res.send({
			success: true,
			data: {
				id: 'string',
				title: 'string',
				content: 'string',
				completed: true,
				createdDate: new Date(),
				updatedDate: new Date(),
				isDeleted: true,
			}
		});
	})
	.put<UpdateTodoResponse, UpdateTodoRequest>((req, res) => {
		res.send({
			success: true,
			data: {
				id: 'string',
				title: 'string',
				content: 'string',
				completed: true,
				createdDate: new Date(),
				updatedDate: new Date(),
				isDeleted: true,
			}
		});
	})
	.delete<DeleteTodoResponse, DeleteTodoRequest>((req, res) => {
		res.send({
			success: true,
			data: {
				id: 'string',
				title: 'string',
				content: 'string',
				completed: true,
				createdDate: new Date(),
				updatedDate: new Date(),
				isDeleted: true,
			}
		});
	})

myRouter.route('/todo')
	.get<TodoListResponse, TodoListRequest, TodoListParams>((req, res) => {
		res.send({
			success: true,
			data: {
				todos: [
					{
						id: 'string',
						title: 'string',
						content: 'string',
						completed: true,
						createdDate: new Date(),
						updatedDate: new Date(),
						isDeleted: true,
					}
				],
				paging: {
					total_pages: 1,
					current_page: 1,
					is_last_page: true,
				}
			}
		});
	})

export default todoRouter;