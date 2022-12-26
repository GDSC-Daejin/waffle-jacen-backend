import ResponseWrapper, { PagingResponse } from './index';
import { TodoModel } from '../db/todo';

/* single todo response */
export type CreateTodoResponse<ErrorType = string> = ResponseWrapper<
  TodoModel,
  ErrorType
>;
export type ReadTodoResponse<ErrorType = string> = ResponseWrapper<
  TodoModel,
  ErrorType
>;
export type UpdateTodoResponse<ErrorType = string> = ResponseWrapper<
  TodoModel,
  ErrorType
>;
export type DeleteTodoResponse<ErrorType = string> = ResponseWrapper<
  TodoModel,
  ErrorType
>;

/* multiple todo response */
export type TodoListResponse<ErrorType = string> = ResponseWrapper<
  PagingResponse & { todos: TodoModel[] },
  ErrorType
>;

/* trash todo response */
export type TodoTrashListResponse<ErrorType = string> = ResponseWrapper<
  PagingResponse & { todos: TodoModel[] },
  ErrorType
>;
export type RecoverTodoResponse<ErrorType = string> = ResponseWrapper<
  TodoModel,
  ErrorType
>;
