import { TodoModel } from "../db/todo";
import { PagingParams } from './index.d';

/* single todo request */
export interface CreateTodoRequest extends Pick<TodoModel, 'title' | 'content'> { }
export interface ReadTodoRequest { }
export interface UpdateTodoRequest extends Pick<TodoModel, 'title' | 'content' | 'completed'> { }
export interface DeleteTodoRequest { }

/* multiple todo request */
export interface TodoListRequest { }
export interface TodoListParams extends PagingParams<TodoModel> { }

/* trash todo request */
export interface TodoTrashListRequest { }
export interface TodoTrashListParams extends PagingParams<TodoModel> { }
export interface RecoverTodoRequest { }