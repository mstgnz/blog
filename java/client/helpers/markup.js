export default function Markup({ html }){
    return(
        <div dangerouslySetInnerHTML={createMarkup(html)} />
    )
}

function createMarkup(html) {
    return {__html: html};
}