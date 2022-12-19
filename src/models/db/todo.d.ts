export interface TodoModel {
	id: string;
	title: string;
	content: string;
	completed: boolean;
	createdDate: Date;
	updatedDate: Date;
	isDeleted: boolean;
}