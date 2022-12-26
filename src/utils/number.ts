export const parseIntOrDefault = <T>(
  value: T | undefined | null,
  defaultValue: number
) => {
  if (typeof value !== 'string') return defaultValue;
  const parsedResult = parseInt(value);
  if (Number.isNaN(parsedResult)) return defaultValue;
  return parsedResult;
};
