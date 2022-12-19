import express from "express";
import { json } from "body-parser";
import todoRouter from "./routes/todo";

const port = 3000;
const app = express();

app.use(json());
app.use('/todo', todoRouter);

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})

