import 'bootstrap/dist/css/bootstrap.css';

import Auth from '../components/auth';
import Header from "../components/header";
import Footer from "../components/footer";
import Head from 'next/head';
import jwt from 'jsonwebtoken';

const App = ({ Component, pageProps }) => {

    const isAuth = () => {
        if(pageProps.isAuth === true){
            if(typeof window === "undefined"){
                // link yenlenmesinde de token olmasına rağmen localstorage okunamadığı için yine login gidecek buna bi çözüm bakıcaz sonra
                return false;
            }else{
                const token = localStorage.getItem("token");
                try {
                    jwt.verify(token, pageProps.JWT_SECRET_KEY, { algorithm: 'HS512'});
                    return true;
                } catch (error) {
                    return false;
                }
            }
        }
        return true;
    }


    return (
        <section>
            <Head>
                <title>My Blog</title>
                <link rel="icon" href="/favicon.ico" />
                <meta charSet="utf-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Mesut GENEZ Blog Project For Java SpringBoot And NextJS" />
                <meta name="author" content="Mesut GENEZ" />
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
                <script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossOrigin="anonymous"></script>
                <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
                <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
                {
                    isAuth() ?
                    <>
                        <link href="/global.css" rel="stylesheet" type="text/css" />
                        <script type="text/javascript" src="/global.js"></script>
                    </> :
                    <>
                        <link href="/login.css" rel="stylesheet" type="text/css" />
                        <script type="text/javascript" src="/login.js"></script>
                    </>
                }
            </Head>
            <div className="container-fluid">
                {
                    isAuth()
                    ? 
                    <>
                        <Header {...pageProps} />
                        <Component {...pageProps} />
                        <Footer {...pageProps} />
                    </>
                    :
                    <Auth {...pageProps} />
                }
            </div>
        </section>
    )
}

export default App;