import {useState} from "react"
import axios from "axios";
import {Link, useNavigate } from "react-router-dom";
import api from "./api/axoisInstance"
function Login(){
    const [email,setEmail] =useState("");
    const [password,setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async ()=>{
    const loginRequest={
        email,
        password
    };
    if(loginRequest.email.trim()===""){
            alert("Email is required");
            return;
        }
        if(loginRequest.password.trim()===""){
            alert("Password is Required");
            return;
        }
       try{
        const response = await axios.post("http://localhost:8080/auth/login",loginRequest);
        localStorage.setItem("token",response.data.token);
        alert("Login Successful");
        navigate("/dashboard");
       }
       catch(error){
        if(error.response){
            alert(error.response.data.message)
        }
        else{
            alert("unable to connect to server");
        }

       }
}

return (
    <>

    <h2>Login Form</h2>

    <input type="email" placeholder="Enter your registered email " value={email} onChange={(event) =>{setEmail(event.target.value)}}/>
    <input type="password" placeholder="Enter Password " value={password} onChange={(event) =>{setPassword(event.target.value)}}/>


    <p>Email : {email}</p>
    <p>Password : {password}</p>
    <button onClick={handleLogin}>Login</button>

    <p>
        Don't have account? <Link to='/register'>Register</Link>
    </p>
    </>
)
}

export default Login;