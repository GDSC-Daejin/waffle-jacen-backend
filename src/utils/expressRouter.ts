import {
  RouteParameters,
  RequestHandler,
  IRouter,
} from 'express-serve-static-core';
import { ParsedQs } from 'qs';

// interface RouterMethod<Path extends string, T extends Express, P extends RequestHandler<Path>>{
// 	<
// 		ResBody = string,
// 		ReqBody = never,
// 		ReqQuery = ParsedQs,
// 		Locals extends Record<string, any> = Record<string, any>,
// 	>(handler: RequestHandler<P, keyof ResBody extends never ? never : ResBody, ReqBody | undefined, ToParams<ReqQuery> | undefined, Locals>) => T
// }

type ToParams<T> = T extends object
  ? T extends Date
    ? string
    : T extends Array<any>
    ? string
    : { [K in keyof T]: ToParams<T[K]> }
  : string;

export class ExpressRouter<T extends IRouter> {
  private app: T;
  constructor(app: T) {
    this.app = app;
  }

  /**
   *
   * @param path url for endpoint
   * @returns http method handlers
   *
   * @example
   * const app = express();
   * const router = new ExpressRouter(app);
   * router.route('/todo/:id')
   * 	.get<ReadTodoRequestBody, ReadTodoResponseBody>((req, res) => {
   * 		// ...
   * 	})
   * 	.post<CreateTodoRequestBody, CreateTodoResponseBody>((req, res) => {
   * 		// ...
   * 	})
   */
  route = <Path extends string, P = RouteParameters<Path>>(path: Path) => {
    const routeHandlers = {
      all: <
        ResBody = string,
        ReqBody = never,
        ReqQuery = ParsedQs,
        Locals extends Record<string, any> = Record<string, any>
      >(
        handler: RequestHandler<
          P,
          keyof ResBody extends never ? never : ResBody,
          ReqBody | undefined,
          ToParams<ReqQuery> | undefined,
          Locals
        >
      ) => {
        this.app.all(path, handler);
        return routeHandlers;
      },

      get: <
        ResBody = string,
        ReqBody = never,
        ReqQuery = ParsedQs,
        Locals extends Record<string, any> = Record<string, any>
      >(
        handler: RequestHandler<
          P,
          keyof ResBody extends never ? never : ResBody,
          ReqBody | undefined,
          ToParams<ReqQuery> | undefined,
          Locals
        >
      ) => {
        this.app.get(path, handler);
        return routeHandlers;
      },

      post: <
        ResBody = string,
        ReqBody = never,
        ReqQuery = ParsedQs,
        Locals extends Record<string, any> = Record<string, any>
      >(
        handler: RequestHandler<
          P,
          keyof ResBody extends never ? never : ResBody,
          ReqBody | undefined,
          ToParams<ReqQuery> | undefined,
          Locals
        >
      ) => {
        this.app.post(path, handler);
        return routeHandlers;
      },

      put: <
        ResBody = string,
        ReqBody = never,
        ReqQuery = ParsedQs,
        Locals extends Record<string, any> = Record<string, any>
      >(
        handler: RequestHandler<
          P,
          keyof ResBody extends never ? never : ResBody,
          ReqBody | undefined,
          ToParams<ReqQuery> | undefined,
          Locals
        >
      ) => {
        this.app.put(path, handler);
        return routeHandlers;
      },

      delete: <
        ResBody = string,
        ReqBody = never,
        ReqQuery = ParsedQs,
        Locals extends Record<string, any> = Record<string, any>
      >(
        handler: RequestHandler<
          P,
          keyof ResBody extends never ? never : ResBody,
          ReqBody | undefined,
          ToParams<ReqQuery> | undefined,
          Locals
        >
      ) => {
        this.app.delete(path, handler);
        return routeHandlers;
      },

      patch: <
        ResBody = string,
        ReqBody = never,
        ReqQuery = ParsedQs,
        Locals extends Record<string, any> = Record<string, any>
      >(
        handler: RequestHandler<
          P,
          keyof ResBody extends never ? never : ResBody,
          ReqBody | undefined,
          ToParams<ReqQuery> | undefined,
          Locals
        >
      ) => {
        this.app.patch(path, handler);
        return routeHandlers;
      },

      options: <
        ResBody = string,
        ReqBody = never,
        ReqQuery = ParsedQs,
        Locals extends Record<string, any> = Record<string, any>
      >(
        handler: RequestHandler<
          P,
          keyof ResBody extends never ? never : ResBody,
          ReqBody | undefined,
          ToParams<ReqQuery> | undefined,
          Locals
        >
      ) => {
        this.app.options(path, handler);
        return routeHandlers;
      },

      head: <
        ResBody = string,
        ReqBody = never,
        ReqQuery = ParsedQs,
        Locals extends Record<string, any> = Record<string, any>
      >(
        handler: RequestHandler<
          P,
          keyof ResBody extends never ? never : ResBody,
          ReqBody | undefined,
          ToParams<ReqQuery> | undefined,
          Locals
        >
      ) => {
        this.app.head(path, handler);
        return routeHandlers;
      },
    };
    return routeHandlers;
  };
}
