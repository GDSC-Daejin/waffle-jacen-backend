import express from 'express';
import todoRouter from './routes/todo';

const port = 3000;
const app = express();

// app.use(bodyParser.urlencoded({ extended: false }))
app.use(express.json());
app.use('/todo', todoRouter);

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});
