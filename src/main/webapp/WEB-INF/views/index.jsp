<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<body>
<h2>Upload yours Images</h2>

<div class="row">
    <form method="post" action="/uploadImages" enctype="multipart/form-data">
        <div class="file_upload">
            <input type="file" name="Image 1">
            <input type="file" name="Image 2">
        </div>
    </form>
</div>


</body>
</html>
