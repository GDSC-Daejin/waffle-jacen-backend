import { MongoClient } from 'mongodb';
import { TodoModel } from '../models/db/todo';

const client = new MongoClient('mongodb://127.0.0.1:27017');
const todoDb = client.db('todo');
const collections = {
  todo: todoDb.collection<TodoModel>('todo'),
};

export default collections;
