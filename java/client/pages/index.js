import Link from 'next/link';
import Markup from '../helpers/markup';

const Home = ({blogs}) => {
    
    return(
        <section id="index">
            <div className="container px-4 px-lg-5">
                <div className="row gx-4 gx-lg-5 justify-content-center">
                    <div className="col-md-10 col-lg-8 col-xl-7">
                        
                        { blogs && blogs.map( blog => (
                            <div key={blog.id}>
                            <div className="post-preview">
                                <Link href={`/${blog.slug}`}>
                                    <a>
                                    <h2 className="post-title">{blog.title}</h2>
                                    <h6 className="post-subtitle">
                                        <Markup html={blog.shortText} />
                                    </h6>
                                    </a>
                                </Link>
                                <p className="post-meta">
                                    Posted by
                                    <a href="#!">{blog.fullName}</a>
                                    on {blog.updateDate}
                                </p>
                            </div>
                            <hr className="my-4" />
                            </div>
                        )) }
                        
                        <div className="d-flex justify-content-end mb-4"><a className="btn btn-primary text-uppercase" href="#!">More Posts →</a></div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export async function getServerSideProps() {
    let blogs = null;
    try {
        const request = await fetch("http://localhost:8080/api/blogs");
        blogs = await request.json();
    } catch (error) {
        console.log(error)
    }
  
    return { props: {...process.env, blogs} }

}

export default Home;