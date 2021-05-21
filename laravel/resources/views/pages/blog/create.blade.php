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
                        <input type="text" name="title" class="form-control" placeholder="Başlık" required data-validation-required-message="Please enter your title.">
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>Content</label>
                        <textarea rows="5" name="short_text" class="form-control" placeholder="Kısa Metin" required data-validation-required-message="Please enter a short text."></textarea>
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>Content</label>
                        <textarea rows="10" name="long_text" class="form-control" placeholder="Uzun Metin" required data-validation-required-message="Please enter a content."></textarea>
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <br>
                <div id="success"></div>
                <button type="submit" class="btn btn-primary" id="sendMessageButton">Kaydet</button>
            </form>
        </div>
    </div>
</div>
    
@endsection