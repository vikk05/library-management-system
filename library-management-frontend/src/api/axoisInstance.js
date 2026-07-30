import axios, { HttpStatusCode } from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080"
});

api.interceptors.request.use((config)=>{  // we use request.use Because we want to execute some logic before every HTTP request is sent.
     //Q2. Why does it receive config?
        //config represents the HTTP request that Axios is about to send. It contains information such as:
         //URL
         //Method (GET, POST, PUT, DELETE)
         //Headers
         //Request body
         //Timeout, etc.

    const token = localStorage.getItem("token");

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;// why return config  Because Axios needs the modified request object 
    //to continue sending the request.Without return config, Axios doesn't receive the request configuration back.
})

api.interceptors.response.use(
    (response)=>{
        return response;
    },

    (error) =>{
        if(error.response?.status === 401){
            localStorage.removeItem("token");
            window.location.href = "/login" ;
        }
        return Promise.reject(error);
    }
)

export default api