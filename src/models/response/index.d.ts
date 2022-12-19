type ResponseWrapper<T, E = string> = {
  success: true;
  data: T;
} | {
  success: false;
  data: E;
}
export default ResponseWrapper;

export interface PagingResponse {
	paging: {
		total_pages: number,
		current_page: number,
		is_last_page: boolean
	}
}
