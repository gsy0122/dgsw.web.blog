// $(() => {
//     init();
// });
//
// let init = () => {
//
// };

function openPostDialog() {
    if (user == null) return;
    $('#post').show(500);
}

function closePostDialog() {
    $('#post').hide(500);
}

let user = null;

async function login() {
    user = await $.get("/user/read/1");
    console.log(JSON.stringify(user.data));
    $('#user').html(`
        <img src="https://cdn2.iconfinder.com/data/icons/4web-3/139/header-account-image-line-512.png" 
             style="width: 200px; height: 200px;"/>
        <div>${user.data.account}</div>
        <div>${user.data.name}</div>
        <div>${user.data.email}</div>
        <div>${user.data.phone}</div>
    `)
}

async function addPost() {
    let post = {
        userId: user.data.id,
        title: $('#new-title').val(),
        content: $('#new-content').val()
    };
    console.log(JSON.stringify(post));
    let response = await $.ajax({
        type: 'post',
        url: '/post/create',
        contentType: 'application/json',
        data: JSON.stringify(post)
    });
    console.log(JSON.stringify(response.data));
    addPostLine(response.data);
    viewPost(response.data.id);
    closePostDialog();
}

async function editPost(button, id) {
    if ($(button).text() == '수정') {
        let title = $('#edit-title').html();
        let inputTitle = `<input id="input-title" size="100" value="${title}">`;
        let content = $('#edit-content').html();
        let inputContent = `<textarea id="input-content" cols="100" rows="30" style="position: relative; top: 15px;">${content}</textarea>`;
        $('#edit-title').html(inputTitle);
        $('#edit-content').html(inputContent);
        $(button).text('확인');
        $(button).next().text('취소');
    } else {
        let post = {
            title: $('#input-title').val(),
            content: $('#input-content').val()
        };
        let response = await $.ajax({
            type: 'put',
            url: `/post/update/${id}`,
            contentType: 'application/json',
            data: JSON.stringify(post)
        });
        console.log(JSON.stringify(response));
        $(`#line${id}`).html(`
            <div onclick="viewPost(${id})">${response.data.title}</div>
        `)
        viewPost(id);
    }
}

async function removePost(button, id) {
    if ($(button).text() == '삭제') {
        try {
            let response = await $.ajax({
                type: 'delete',
                url: `/post/delete/${id}`,
                contentType: 'application/json'
            });
            if (response) {
                $(`#line${id}`).remove();
                $('.content').html("");
            } else {
                alert('삭제에 실패하였습니다.');
            }
        } catch (error) {
            console.log(JSON.stringify(error));
        }
    } else {
        viewPost(id);
    }
}

async function viewPost(id) {
    let post = await $.get(`/post/read/${id}`);
    $('.content').html(`
        <div id="edit-title">${post.data.title}</div><hr>
        <div style="text-align: right; position: relative; right: 10px;">
            <div>${post.data.userId}</div>
            <div>${post.data.created}</div>
        </div>
        <div id="edit-content">${post.data.content}</div>
        <div style="text-align: right; position: relative; right: 10px; top: 10px;">
            <button onclick="editPost(this, ${post.data.id})">수정</button>
            <button onclick="removePost(this, ${post.data.id})">삭제</button>
        </div>
    `)
}

async function getPostList() {
    try {
        let response = await $.get('/post/read');
        for (let i = 0; i < response.data.length; i++) {
            addPostLine(response.data[i]);
        }
        viewPost(response.data[response.data.length - 1].id);
    } catch (error) {
        $('#post-list').html(JSON.stringify(error));
    }
}

function addPostLine(data) {
    $('#post-list').prepend(`
        <div id="line${data.id}"
             class="post-line" 
             onclick="viewPost(${data.id})" 
             style="position: relative; bottom: 5px;">${data.title}</div>
    `);
}

login();
getPostList();