import Link from 'next/link';
import Router, {useRouter} from 'next/router';

export default function Header(){

    const router = useRouter();

    const getToken = () => {
        if(typeof window === "undefined"){
            return false;
        }else{
            return localStorage.getItem("token") !== null;
        }
    }

    const Logout = () => {
        localStorage.removeItem("token");
        router.replace("/");
    }

    return (
        <section id="header">
            <nav className="navbar navbar-expand-lg navbar-light" id="mainNav">
                <div className="container px-4 px-lg-5">
                    <Link href="/"><a className="navbar-brand">My Blog</a></Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                        Menu
                        <i className="fas fa-bars"></i>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarResponsive">
                        <ul className="navbar-nav ms-auto py-4 py-lg-0">
                            <li className="nav-item"><Link href="/"><a className="nav-link px-lg-3 py-3 py-lg-4">Home</a></Link></li>
                            <li className="nav-item"><Link href="/about"><a className="nav-link px-lg-3 py-3 py-lg-4">About</a></Link></li>
                            <li className="nav-item"><Link href="/account"><a className="nav-link px-lg-3 py-3 py-lg-4">Profile</a></Link></li>
                            {
                                getToken() &&
                                <li className="nav-item"><a id="logout" className="nav-link px-lg-3 py-3 py-lg-4" onClick={Logout}>Logout</a></li>
                            }
                        </ul>
                    </div>
                </div>
            </nav>
            <header className="masthead">
                <div className="container position-relative px-4 px-lg-5">
                    <div className="row gx-4 gx-lg-5 justify-content-center">
                        <div className="col-md-10 col-lg-8 col-xl-7">
                            <div className="site-heading">
                                <h1>My Blog</h1>
                                <span className="subheading">A Blog Theme by Mesut GENEZ</span>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <style jsx>{`
                #logout{
                    cursor:pointer
                }
                .masthead{
                    background-image: url('/assets/img/home-bg.jpg')
                }
            `}
            </style>
        </section>
    )
}