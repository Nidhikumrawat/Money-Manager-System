import Dashboard from '../components/Dashboard.jsx';
import { useUser } from "../hooks/useUser";


const Expense = () =>{
     useUser();
    return (
       <Dashboard  activeMenu="Expense">
                This is expense 
            </Dashboard>
    )
}

export default Expense;