type RequireSome<T extends {}, K extends keyof T> = {
  [key in keyof Omit<T, K>]?: Omit<T, K>[key];
} & {
  [key in K]-?: T[key];
};
