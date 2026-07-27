import axios from "axios";

function Registration() {
 const [name,setName] = useState("");
 const [email,setEmail]=useState("");
 const [password,setPassword] = useState("");
 const handleRegister = async () => {
    try{
        const response = await axios.post("http://localhost:8080/api/auth/register",user);
        alert("Registered Sucessfully");
    }catch(error){
        alert("Registration failed");
    }

        const user ={
            name,
            email,
            password
        };

        if(user.name.trim()===""){
            alert("Name is Required");
            return;
        }
        else if(user.email.trim()===""){
            alert("Email is required");
            return;
        }
        else if(user.password.trim()===""){
            alert("Password is Required");
            return;

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

    <p>Name: {name}</p>
    <p>Email:{email}</p>
    <p>Password:{password}</p>
    <button onClick={handleRegister}>Register</button>

    </>
);
}

export default Registration;
