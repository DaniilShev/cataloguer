<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Каталогизатор</title>

    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap/dist/css/bootstrap.min.css" />
    <link href="css/style.css" rel="stylesheet" />
</head>
<body>
    <div id="app" class="container">
        <ul class="nav justify-content-center my-3">
            <li class="nav-item">
                <router-link active-class="active" to="/" class="nav-link">Каталог</router-link>
            </li>
            <li class="nav-item">
                <router-link active-class="active" to="/authors" class="nav-link">Авторы</router-link>
            </li>
            <li class="nav-item">
                <router-link active-class="active" to="/publishers" class="nav-link">Издательства</router-link>
            </li>
        </ul>
        <router-view></router-view>
    </div>

    <script type="text/x-template" id="book-list-template">
        <div>
            <div v-if="books.length">
                <div class="list-group mb-4">
                    <div class="list-group-item list-group-item-action flex-column align-items-start"
                         v-for="book in books" :key="book.id">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">{{ book.name }}</h5>
                            <small :class="(book.inLibrary) ? 'text-success' : 'text-dark'">{{ (book.inLibrary) ? 'в библиотеке' : 'выдана читателю' }}</small>
                        </div>
                        <p class="mb-1">Находится в <b>{{ book.room }}</b> комнате на <b>{{ book.shelf }}</b> полке</p>
                        <small>Издательство: <router-link
                                :to="'/publishers/' + book.publisher.id" class="badge"><u>{{ book.publisher.name }}</u></router-link></small>
                        <small class="ml-2">Автор: <router-link
                                :to="'/authors/' + book.author.id" class="badge"><u>{{ book.author.fullname }}</u></router-link></small>
                    </div>
                </div>
                <nav aria-label="Страницы списка">
                    <ul class="pagination">
                        <li :class="['page-item', (!pageNumber) ? 'disabled' : '']">
                            <a class="page-link" href="#" :tabindex="(pageNumber == 1) ? '-1' :''"
                               @click="go(pageNumber - 1)">Назад</a>
                        </li>
                        <li :class="['page-item', (pageNumber == page - 1) ? 'active' : '']" v-for="page in totalPages">
                            <a class="page-link" @click="go(page - 1)">{{ page }} <span class="sr-only"
                                                                                        v-if="pageNumber == page - 1">(текущая)</span></a>
                        </li>
                        <li :class="['page-item', (pageNumber == totalPages - 1) ? 'disabled' : '']">
                            <a class="page-link" :tabindex="(pageNumber == totalPages) ? '-1' :''"
                               @click="go(pageNumber + 1)">Далее</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="alert alert-warning" role="alert" v-else>
                Ничего не найдено
            </div>
        </div>
    </script>

    <script type="text/x-template" id="catalog-template">
        <div>
            <div class="my-5 text-center">
                <h1>Каталог библиотеки</h1>
            </div>
            <div class="input-group mb-4">
                <input type="text" class="form-control"
                       placeholder="Введите название книги, название издательства или имя автора книги.."
                       aria-label="Введите название книги, название издательства или имя автора книги.." aria-describedby="basic-addon2"
                       v-model="query">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" @click="go(0)">Поиск</button>
                </div>
            </div>
            <div class="alert alert-danger" role="alert" v-if="error">
                {{ error }}
            </div>
            <book-list v-if="!loading" :books="books" :pageNumber="pageNumber" :totalPages="totalPages" @go="go"></book-list>
            <div class="alert alert-primary" role="alert" v-else>
                Загрузка..
            </div>
        </div>
    </script>

    <script type="text/x-template" id="author-books-template">
        <div>
            <div class="my-5 text-center">
                <h1>Список книг автора {{ author.fullname }}</h1>
            </div>
            <div class="alert alert-danger" role="alert" v-if="error">
                {{ error }}
            </div>
            <book-list v-if="!loading" :books="books" :pageNumber="pageNumber" :totalPages="totalPages" @go="go"></book-list>
            <div class="alert alert-primary" role="alert" v-else>
                Загрузка..
            </div>
        </div>
    </script>

    <script type="text/x-template" id="publisher-books-template">
        <div>
            <div class="my-5 text-center">
                <h1>Список книг издательства {{ publisher.name }}</h1>
            </div>
            <div class="alert alert-danger" role="alert" v-if="error">
                {{ error }}
            </div>
            <book-list v-if="!loading" :books="books" :pageNumber="pageNumber" :totalPages="totalPages" @go="go"></book-list>
            <div class="alert alert-primary" role="alert" v-else>
                Загрузка..
            </div>
        </div>
    </script>

    <script type="text/x-template" id="authors-template">
        <div>
            <div class="my-5 text-center">
                <h1>Список авторов</h1>
            </div>
            <div class="alert alert-danger" role="alert" v-if="error">
                {{ error }}
            </div>
            <div v-if="!loading">
                <div class="list-group mb-4"  v-if="authors.length">
                    <div class="list-group-item d-flex justify-content-between align-items-center" v-for="author in authors" :key="author.id">
                        <span><b>{{ author.fullname }}</b> ({{ author.country }}, {{ author.birthYear }})</span>
                        <span><router-link :to="'/authors/' + author.id" class="badge badge-primary"><u>Книги в каталоге</u></router-link></span>
                    </div>
                </div>
                <div class="alert alert-warning" role="alert" v-else>
                    Не найдено ни одного автора
                </div>
            </div>
            <div class="alert alert-primary" role="alert" v-else>
                Загрузка..
            </div>
        </div>
    </script>

    <script type="text/x-template" id="publishers-template">
        <div>
            <div class="my-5 text-center">
                <h1>Список издательств</h1>
            </div>
            <div class="alert alert-danger" role="alert" v-if="error">
                {{ error }}
            </div>
            <div v-if="!loading">
                <div class="list-group mb-4"  v-if="publishers.length">
                    <div class="list-group-item d-flex justify-content-between align-items-center" v-for="publisher in publishers" :key="publisher.id">
                        <span><b>{{ publisher.name }}</b> ({{ publisher.country }})</span>
                        <span><router-link :to="'/publishers/' + publisher.id" class="badge badge-primary"><u>Книги в каталоге</u></router-link></span>
                    </div>
                </div>
                <div class="alert alert-warning" role="alert" v-else>
                    Не найдено ни одного издательства
                </div>
            </div>
            <div class="alert alert-primary" role="alert" v-else>
                Загрузка..
            </div>
        </div>
    </script>

    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/vue-router/dist/vue-router.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/lodash"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/whatwg-fetch"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/promise-polyfill/dist/polyfill.min.js"></script>

    <script type="text/javascript" src="js/index.js"></script>
</body>