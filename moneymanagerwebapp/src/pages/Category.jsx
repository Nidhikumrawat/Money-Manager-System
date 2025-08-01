import React, { useEffect, useState } from 'react';
import Dashboard from '../components/Dashboard.jsx';
import useUser from "../hooks/useUser";
import { Plus } from 'lucide-react';
import CategoryList from '../components/CategoryList.jsx'; // adjust path as needed
import { API_ENDPOINTS } from '../util/apiEndpoints.js';
import toast from 'react-hot-toast';
import axiosConfig from '../util/axiosConfig.jsx';
import Modal from '../components/Modal';
import Input from "../components/Input.jsx";
import AddCategoryForm from '../components/AddCategoryForm.jsx';

const Category = () => {
    useUser();
    const [loading, setLoading] = useState(false);
    const [categoryData, setCategoryData] = useState(null);
    const [openAddCategoryModel, setOpenAddCategoryModel] = useState(false);
    const [openEditCategoryModel, setOpenEditCategoryModel] = useState(false);
    const [selectedCategory, setSelectedCategory] = useState(null);

    const fetchCategoryDetails = async () => {
        if (loading) return;

        setLoading(true);

        try {
            const response = await axiosConfig.get(API_ENDPOINTS.GET_ALL_CATEGORIES);
            if (response.status === 200) {
                console.log('categories', response.data);
                setCategoryData(response.data);
            }
        } catch (error) {
            console.error('Something went wrong. Please try again.', error);
            toast.error(error.message);
        } finally {
            setLoading(false);
        }
    }
    //useeffect to call the method 
    useEffect(() => {
        fetchCategoryDetails();
    }, []);

    const handleAddCategory =  async(category) => {
        const { name, type, icon } = category;

        if (!name.trim()) {
            toast.error("Category Name is required");
            return;
        }

        //check if the category already exists
        const isDuplicate = categoryData.some((category) =>{
           return category.name.toLowerCase() === name.trim().toLowerCase();
        })

        if(isDuplicate){
            toast.error("Category Name already exists");
            return ;
        }

        try {
            const response = await axiosConfig.post(API_ENDPOINTS.ADD_CATEGORY, { name, type, icon });
            if (response.status === 201) {
                toast.success("Category added successfully");
                setOpenAddCategoryModel(false);
                fetchCategoryDetails();
            }
        } catch (error) {
            console.error('Error adding category', error);
            toast.error(error.response?.data?.message || "Failed to add category.");
        }
    };

const handleEditCategory = (categoryToEdit)=>{
    setSelectedCategory(categoryToEdit);
    setOpenEditCategoryModel(true);
}

const handleUpdateCategory = async (updatedCategory) =>{
    const {id,name,type, icon} = updatedCategory;
    if(!name.trim()){
        toast.error("Category Name is required");
        return ;
    }
    if(!id){
        toast.error("Category ID is missing for update");
        return;
    }

    try{
        const response = await axiosConfig.put(API_ENDPOINTS.UPDATE_CATEGORY(id), {name, type,icon});
        setOpenEditCategoryModel(false)
        setSelectedCategory(null);
        toast.success("Category updated successfully");
        fetchCategoryDetails();
    }catch(error){
console.error('Error updating category:', error.response?.data?.message || error.message);
toast.error(error.response?.data?.message || "Failed to upadate category");
    }
}

    return (
        <Dashboard activeMenu="Category">
            <div className="my-5 mx-auto">
                {/* Add button to add category */}

                <div className="flex justify-between items-center mb-5">
                    <h2 className="text-2xl font-semibold"> All Categories </h2>
                    <button
                        onClick={() => setOpenAddCategoryModel(true)}
                        className="add-btn flex items-center gap-1 text-green-500">
                        <Plus size={15} />
                        Add Category
                    </button>
                </div>
                {/*  Category list */}
                <CategoryList categories={categoryData || []} onEditCategory={handleEditCategory} />

                {/* Additng category modal */}
                <Modal
                    isOpen={openAddCategoryModel}
                    onClose={() => setOpenAddCategoryModel(false)}
                    title="Add Category">
                    Category form
                    <AddCategoryForm onAddCategory={handleAddCategory} />
                </Modal>
                {/* Updating category modal */}
                <Modal
                   onClose={()=> {
                        setOpenEditCategoryModel(false)
                        setSelectedCategory(null)
                   }}
                    isOpen={openEditCategoryModel}
                   title="Update Category"
                   >

                    <AddCategoryForm
                        initialCategoryData = {selectedCategory}
                        onAddCategory={handleUpdateCategory}
                        isEditing={true}
                    />
                </Modal>
            </div>
        </Dashboard>
    )
}

export default Category;