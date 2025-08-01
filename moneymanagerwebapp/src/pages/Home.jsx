import Dashboard from "../components/Dashboard";
import {useUser} from "../hooks/useUser.jsx";
const Home = () =>{
    useUser();
    
    return (
        <div>
            <Dashboard activeMenu="Dashboard">
                This is home 
            </Dashboard>
        </div>
    )
}

export default Home;