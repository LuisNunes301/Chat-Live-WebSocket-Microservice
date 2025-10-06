export interface User {
    name: string;
    email: string;
    password: string;
}

export interface LoginRequest {
    email: string,
    password: string
}


export interface LoginResponse {
    message: string,
    email: string,
    expiresIn: number
}