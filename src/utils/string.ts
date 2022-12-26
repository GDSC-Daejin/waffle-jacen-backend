export const splitOrDefault = (
  str: string | undefined | null,
  separator: string | RegExp,
  defaultValue: Array<string>
) => {
  if (str === null || str === undefined) return defaultValue;

  const result = str.split(separator);
  if (result.length === 0) return defaultValue;
  return result;
};
