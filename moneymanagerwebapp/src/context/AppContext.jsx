import { createContext} from 'react';
import React, { useState, useEffect } from 'react';

export const AppContext = createContext();

export const AppContextProvider = ({children}) =>{

    const [user, setUser] = useState(null);
    const contextValue  = {
       user,
       setUser,
    };

    return(
        <AppContext.Provider value={contextValue}>
            {children}
        </AppContext.Provider>
    )
}