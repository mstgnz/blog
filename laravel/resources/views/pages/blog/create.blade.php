@extends('layout')

@section('content')

<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <form method="post" id="blog">
                @csrf
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>Title</label>
                        <input type="text" name="title" class="form-control" placeholder="title" id="title" required data-validation-required-message="Please enter your title.">
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>Content</label>
                        <textarea rows="5" name="content" class="form-control" placeholder="Content" id="content" required data-validation-required-message="Please enter a content."></textarea>
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <br>
                <div id="success"></div>
                <button type="submit" class="btn btn-primary" id="sendMessageButton">Register</button>
            </form>
        </div>
    </div>
</div>
    
@endsection