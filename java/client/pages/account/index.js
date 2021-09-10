
const Account = () => {
    
    return (
        <div>
            Account page with login
        </div>
    )
}

export async function getServerSideProps() {

    return { props: {...process.env, isAuth:true} }

}

export default Account;