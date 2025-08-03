//export const BASE_URL = 'https://money-manager-system.onrender.com/api/v1.0';

export const BASE_URL = "http://localhost:8088/api/v1.0";
const CLOUDINARY_CLOUD_NAME = "dscxqyafn";

export const API_ENDPOINTS ={    //call the backend services
   LOGIN:"/login",
   REGISTER: "/register",
   GET_USER_INFO:"/profile",
   GET_ALL_CATEGORIES:"/categories",
   ADD_CATEGORY:"/categories",
   UPDATE_CATEGORY:(categoryId) =>`/categories/${categoryId}`,
   GET_ALL_INCOMES:"/incomes",
   CATEGORY_BY_TYPE : (type) => `/categories/${type}`,
   ADD_INCOME:"/incomes",
   DELETE_INCOME:(incomeId) => `/incomes/${incomeId}`,
   INCOME_EXCEL_DOWNLOAD: "/excel/download/income",
   EMAIL_INCOME: "/email/income-excel",
   APPLY_FILTERS: "/filter",
   DASHBOARD_DATA : "/dashboard",
   GET_ALL_EXPENSES: "/expenses",
   ADD_EXPENSE :"/expenses",
   DELETE_EXPENSE : (expenseId) =>`/expenses/${expenseId}`,
   EXPENSE_EXCEL_DOWNLOAD : "/excel/download/expense",
EMAIL_EXPENSE: "/email/excel-excel",
   UPLOAD_IMAGE:`https://api.cloudinary.com/v1_1/${CLOUDINARY_CLOUD_NAME}/image/upload`
}