import Dashboard from '../components/Dashboard.jsx';
import { useUser } from "../hooks/useUser";
const Filter = () =>{
     useUser();
    return (
       <Dashboard  activeMenu="Filters">
                This is filter
            </Dashboard>
    )
}

export default Filter;