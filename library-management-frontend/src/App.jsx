import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Registration from "./Registration";
import Login from "./Login"
import Dashboard from "./Dashboard"
import ProtectedRoutes from './ProtectedRoute';

function App() {
return( 
    <BrowserRouter>

      <Routes>
          <Route path='/register' element={<Registration />}></Route>

          <Route path='/login' element={<Login />}></Route>

          <Route path='/dashboard' element={<ProtectedRoutes>
                                          <Dashboard />
                                            </ProtectedRoutes>} />
     
      </Routes>
  </BrowserRouter>
);
}

export default App