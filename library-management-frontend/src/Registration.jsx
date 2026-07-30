import axios from "axios";
import { useState} from "react"
import { Link, useNavigate } from "react-router-dom";

function Registration() {
 const [name,setName] = useState("");
 const [email,setEmail]=useState("");
 const [password,setPassword] = useState("");
 const [address,setAddress] = useState("");
 const [contactNumber,setContactNumber]= useState("");
 const navigate=useNavigate();


 const handleRegister = async () => {
    const user ={
            name,
            email,
            password,
            address,
            contactNumber
        };
        if(user.name.trim()===""){
            alert("Name is Required");
            return;
        }
        if(user.email.trim()===""){
            alert("Email is required");
            return;
        }
        if(user.password.trim()===""){
            alert("Password is Required");
            return;
        }
        if(user.contactNumber.trim()===""){
            alert("contact number is Required");
            return;
        }
        if(user.address.trim()===""){
            alert("Address is Required");
            return;
        }
        
    try{
        const response = await axios.post("http://localhost:8080/auth/register",user);
        alert(response.data.message);
        navigate("/login");
    }catch(error){

        if(error.response){
            alert(error.response.data.message)
            
        }
        else{
        alert("Unable to connect to server");
        }
    }  

    };


return(
    <>
    
    <h2>Registration Form</h2>

    <input type="text" placeholder="Enter your name" value={name} onChange={(event) => {
        setName(event.target.value);
    }}/>

    <input type="email" placeholder="Enter your email" value={email} onChange={(event)=>{
    setEmail(event.target.value);
    }}/>

    <input type="password" placeholder="Enter your password" value={password} onChange={(event)=>{setPassword(event.target.value)}}/>

    <input type="text" placeholder="Enter your contact number" value={contactNumber} onChange={(event)=>{setContactNumber(event.target.value)}}/>

    <input type="text" placeholder="Enter your address" value={address} onChange={(event)=>{setAddress(event.target.value)}}/>

    <p>Name: {name}</p>
    <p>Email:{email}</p>
    <p>Password:{password}</p>
    <button onClick={handleRegister}>Register</button>
    <p>
        Already have an account? <Link to='/login'>Login</Link>
    </p>

    </>
);
}

export default Registration;
