class Response{

    constructor(res){
        this.res = res;
    }

    success(message="success", result=[], status=200){
        return this.res.status(status).json({
            success: true,
            message:message,
            result:result
        });
    }

    error(message="error", result=[], status=200){
        return this.res.status(status).json({
            success: false,
            message:message,
            result:result
        });
    }

    bad(message="bad", result=[], status=400){
        return this.res.status(status).json({
            success: false,
            message:message,
            result:result
        });
    }

}

module.exports = Response;