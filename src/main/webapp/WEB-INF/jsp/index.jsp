<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.0.0-2/css/bootstrap.min.css" />
    <link href="/css/style.css" rel="stylesheet" />
</head>
<body>
    <div id="app">
        <button type="button" class="btn btn-outline-dark" v-if="window != 'catalog'">Вернуться к каталогу</button>
        <component :is="window"></component>
    </div>

    <catalog inline-template>
        <h2>Каталог библиотеки</h2>
        <div class="input-group mb-3">
            <input type="text" class="form-control"
                   placeholder="Название книги или издательства или имя автора книги"
                   aria-label="Название книги или издательства или имя автора книги" aria-describedby="basic-addon2"
                   v-model="query">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" @click="search">Поиск</button>
            </div>
        </div>
        <book-list :books="books" :pages="pages" @go="handleGo"></book-list>
    </catalog>

    <author-books inline-template>
        <h2>Список книги автора {{ author.fullname }}</h2>
        <book-list :books="books" :pages="pages" @go="handleGo"></book-list>
    </author-books>

    <publisher-books inline-template>
        <h2>Список книги издательства {{ publisher.name }}</h2>
        <book-list :books="books" :pages="pages" @go="handleGo"></book-list>
    </publisher-books>

    <book-list inline-template>
        <div class="list-group">
            <div class="list-group-item list-group-item-action flex-column align-items-start active"
                 v-for="book in books" :key="book.id">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">{{ book.name }}</h5>
                    <small>{{ book.status }}</small>
                </div>
                <small>Издательство: <a class="badge badge-pill badge-dark"><u>{{ book.publisher.name }}</u></a></small>
                <small ml-2>Автор: <a class="badge badge-pill badge-dark"><u>{{ book.author.fullname }}</u></a></small>
            </div>
        </div>
        <nav aria-label="Страницы списка">
            <ul class="pagination">
                <li :class="['page-item', (currentPage == 1) ? 'disabled' : '']">
                    <a class="page-link" href="#" :tabindex="(currentPage == 1) ? '-1' :''" @click="go(currentPage - 1)">Назад</a>
                </li>
                <li :class="['page-item', (currentPage == pageNumber) ? 'active' : '']" v-for="pageNumber in pageCount">
                    <a class="page-link" @click="go(pageNumber)">{{ pageNumber }} <span class="sr-only" v-if="page == currentPage">(текущая)</span></a>
                </li>
                <li :class="['page-item', (currentPage == pageCount) ? 'disabled' : '']">
                    <a class="page-link" :tabindex="(currentPage == pagesN) ? '-1' :''" @click="go(currentPage + 1)">Далее</a>
                </li>
            </ul>
        </nav>
    </book-list>

    <script type="text/javascript" src="webjars/bootstrap/4.0.0-2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="webjars/vue/2.5.13/vue.min.js"></script>
    <script type="text/javascript" src="/js/index.js"></script>
</body>