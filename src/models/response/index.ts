type ResponseWrapper<T, E = string> =
  | {
      success: true;
      data: T;
    }
  | {
      success: false;
      data: E;
    };
export default ResponseWrapper;

export interface PagingResponse {
  paging: {
    total_pages: number;
    current_page: number;
    is_last_page: boolean;
  };
}

export const pagingResult = (
  totalCount: number,
  page: number,
  size: number
) => {
  const totalPages = Math.floor(totalCount / size);
  return {
    total_pages: totalPages,
    current_page: page,
    is_last_page: page + 1 >= totalPages,
  };
};
