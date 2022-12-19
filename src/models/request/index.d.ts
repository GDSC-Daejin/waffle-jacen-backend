export interface PagingParams<T> {
	page : number,
	size : number,
	sort : ('asc'|'desc'|keyof T)[]
}
