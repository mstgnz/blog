import Markup from '../helpers/markup';

const BlogDetail = ({ blog }) => {
    return(
        <article className="mb-4">
            <div className="container px-4 px-lg-5">
                <div className="row gx-4 gx-lg-5 justify-content-center">
                    <div className="col-md-10 col-lg-8 col-xl-7">
                        <Markup html={blog.shortText} />
                        <Markup html={blog.longText} />
                        <p>
                            Placeholder text by <a href="http://spaceipsum.com/">{blog.fullName}</a>
                            &middot; Images by <a href="https://www.flickr.com/photos/nasacommons/">NASA on The Commons</a>
                        </p>
                    </div>
                </div>
            </div>
        </article>
    )
}

export async function getServerSideProps({query}) {

    const request = await fetch("http://localhost:8080/api/blogs/"+query.slug);
    const blog = await request.json();
  
    return { props: {...process.env, blog} }

}

export default BlogDetail;