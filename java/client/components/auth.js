import { useEffect, useState } from 'react';
import Router, {useRouter} from 'next/router';
import axios from 'axios';
import jwt from 'jsonwebtoken';

const Auth = ({ JWT_SECRET_KEY }) => {

    const router = useRouter();

    const [authError, setAuthError] = useState(false);

    const loginUser = async event => {
        event.preventDefault()

        try {
            const request = await axios.post('http://localhost:8080/api/login', {
                email: event.target.email.value,
                password: event.target.password.value
            })
            if(request.data.success){
                const parseToken = verifyToken(request.data.data.token);
                if(parseToken){
                    localStorage.setItem("token",request.data.data.token);
                    router.replace("/account");
                }else{
                    setAuthError('Geçersiz Token!');
                }
            }else{
                setAuthError('Giriş Başarısız!');
            }
        } catch (error) {
            setAuthError('İstek Gönderilemedi!');
        }
        
    }

    const registerUser = async event => {
        event.preventDefault()
        try {
            const request = await axios.post('http://localhost:8080/api/register', {
                firstname: event.target.firstname.value,
                lastname: event.target.lastname.value,
                email: event.target.email.value,
                password: event.target.password.value
            })
            if(request.data.success){
                const parseToken = verifyToken(request.data.data.token);
                if(parseToken){
                    localStorage.setItem("token",request.data.data.token);
                    router.replace("/account");
                }else{
                    setAuthError('Geçersiz Token!');
                }
            }else{
                setAuthError('Kayıt Başarısız!');
            }
        } catch (error) {
            setAuthError('İstek Gönderilemedi!');
        }
    
    }

    const verifyToken = (jwtToken) => {
        try {
            return jwt.verify(jwtToken, JWT_SECRET_KEY, { algorithm: 'HS512'});
        } catch (error) {
            return false;
        }
    }

    return(
        <div className="row">
            <div className="col-md-6 offset-md-3">
                <div className="blmd-wrapp">
                    <div className="blmd-color-conatiner ripple-effect-All"></div>
                    <div className="blmd-header-wrapp ">
                        <div className="blmd-switches">
                             <button className="btn btn-circle btn-lg btn-blmd ripple-effect btn-success blmd-switch-button">
                                <svg className="svg" viewBox="0 0 24 24">
                                    <path fill="#fff" d="M19,13H13V19H11V13H5V11H11V5H13V11H19V13Z" />
                                 </svg>
                              </button>
                        </div> 
                    </div> 
                    <div className="blmd-continer">
                        <form onSubmit={loginUser} className="clearfix" id="login-form">
                            <h1>{authError ? authError : "Login Page"}</h1>
                            <div className="col-md-12">
                                <div className="input-group blmd-form">
                                    <div className="blmd-line">
                                        <input type="text" name="email" autoComplete="off" className="form-control" />
                                        <label className="blmd-label">Email</label>
                                    </div>
                                </div>
                                <div className="input-group blmd-form">
                                    <div className="blmd-line">
                                        <input type="password" name="password" autoComplete="off" className="form-control" />
                                        <label className="blmd-label">Password</label>
                                    </div>
                                </div>
                            </div>
                            <div className="col-sm-12 text-center">
                                <button type="submit" className="btn btn-blmd ripple-effect btn-success btn-lg btn-block">Login</button>
                            </div>
                            <br/>
                        </form>
                        <form onSubmit={registerUser} className="clearfix form-hidden" id="Register-form">
                            <h1>{authError ? authError : "Register Page"}</h1>
                            <div className="col-md-12">
                                <div className="input-group blmd-form">
                                    <div className="blmd-line">
                                        <input type="password" name="firstname" autoComplete="off" className="form-control" />
                                        <label className="blmd-label">First Name</label>
                                    </div>
                                </div>
                                <div className="input-group blmd-form">
                                    <div className="blmd-line">
                                        <input type="password" name="lastname" autoComplete="off" className="form-control" />
                                        <label className="blmd-label">Last Name</label>
                                    </div>
                                </div>
                                <div className="input-group blmd-form">
                                    <div className="blmd-line">
                                        <input type="text" name="email" autoComplete="off" className="form-control" />
                                        <label className="blmd-label">Email</label>
                                    </div>
                                </div>
                                <div className="input-group blmd-form">
                                    <div className="blmd-line">
                                        <input type="password" name="password" autoComplete="off" className="form-control" />
                                        <label className="blmd-label">Password</label>
                                    </div>
                                </div>
                            </div>
                            <div className="col-sm-12 text-center">
                                <button type="submit" className="btn btn-blmd ripple-effect btn-warning btn-lg btn-block">
                                    Register
                                </button>
                            </div>
                            <br/>
                        </form>
                    </div>
                </div>
            </div>
            <style jsx>{`
                .svg{
                    width:24px;height:24px
                }
            `}</style>
        </div>
    )
}

export async function getServerSideProps() {

    return { props: {...process.env, page: "auth"} }

}

export default Auth;