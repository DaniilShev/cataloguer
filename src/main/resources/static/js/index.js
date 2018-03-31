Vue.component('book-list', {
    template: '#book-list-template',
    props: {
        books: {
            type: Array,
            required: true
        },
        totalPages: {
            type: Number,
            default: 1
        },
        pageNumber: {
            type: Number,
            default: 0
        }
    },
    methods: {
        go: function(number) {
            this.$emit('go', number);
        }
    }
});

var catalog = {
    template: '#catalog-template',
    data: function() {
        return {
            query: '',
            loading: true,
            books: [],
            pageNumber: 0,
            pageSize: 5,
            totalPages: 0,
            error: ''
        };
    },
    created: function() {
        this.go(0);
    },
    methods: {
        go(pageNumber) {
            this.pageNumber = pageNumber;
            this.error = '';

            var queryPart = (this.query) ? 'search?q=' + encodeURIComponent(this.query) + '&' : '?';

            var self = this;
            fetch('/books/' + queryPart +'page=' + pageNumber + '&size=' + this.pageSize + '&sort=id')
                .then(function (response) {
                    return response.json();
                })
                .then(function (json) {
                    self.books = json.content || [];
                    self.totalPages = json.totalPages;
                    self.loading = false;
                })
                .catch(function (reason) {
                    self.error = 'Произошла ошибка: ' + reason.message;
                    self.loading = false;
                })
        }
    },
    watch: {
        query: _.debounce(
            function() {
                this.go(0);
            },
            500
        )
    }
};

var authorBooks = {
    template: '#author-books-template',
    data: function() {
        return {
            books: [],
            loading: true,
            pageNumber: 0,
            pageSize: 5,
            totalPages: 0,
            error: ''
        };
    },
    watch: {
        '$route': function() {
            this.go(0);
        }
    },
    computed: {
        author: function() {
            return (this.books.length) ? this.books[0].author : {};
        }
    },
    created: function() {
        this.go(0);
    },
    methods: {
        go: function(pageNumber) {
            this.pageNumber = pageNumber;
            this.error = '';

            var self = this;
            fetch('/authors/' + this.$route.params.id + '/books?page=' + pageNumber + '&size=' + this.pageSize)
                .then(function (response) {
                    return response.json();
                })
                .then(function (json) {
                    self.books = json.content || [];
                    self.totalPages = json.totalPages;
                    self.loading = false;
                })
                .catch(function (reason) {
                    self.error = 'Произошла ошибка: ' + reason.message;
                    self.loading = false;
                })
        }
    }
};

var publisherBooks = {
    template: '#publisher-books-template',
    data: function() {
        return {
            books: [],
            loading: true,
            pageNumber: 0,
            pageSize: 5,
            totalPages: 0,
            error: ''
        };
    },
    watch: {
        '$route': function() {
            this.go(0);
        }
    },
    computed: {
        publisher: function() {
            return (this.books.length) ? this.books[0].publisher : {};
        }
    },
    created: function() {
        this.go(0);
    },
    methods: {
        go: function(pageNumber) {
            this.pageNumber = pageNumber;
            this.error = '';

            var self = this;
            fetch('/publishers/' + this.$route.params.id + '/books?page=' + pageNumber + '&size=' + this.pageSize)
                .then(function (response) {
                    return response.json();
                })
                .then(function (json) {
                    self.books = json.content || [];
                    self.totalPages = json.totalPages;
                    self.loading = false;
                })
                .catch(function (reason) {
                    self.error = 'Произошла ошибка: ' + reason.message;
                    self.loading = false;
                })
        }
    }
};

var authors = {
    template: '#authors-template',
    data: function() {
        return {
            authors: [],
            loading: true,
            error: ''
        };
    },
    created: function() {
        var self = this;
        fetch('/authors/')
            .then(function (response) {
                return response.json();
            })
            .then(function (authors) {
                self.authors = authors;
                self.loading = false;
            })
            .catch(function (reason) {
                self.error = 'Произошла ошибка: ' + reason.message;
                self.loading = false;
            })
    }
};

var publishers = {
    template: '#publishers-template',
    data: function() {
        return {
            publishers: [],
            loading: true,
            error: ''
        };
    },
    created: function() {
        var self = this;
        fetch('/publishers/')
            .then(function (response) {
                return response.json();
            })
            .then(function (publishers) {
                self.publishers = publishers;
                self.loading = false;
            })
            .catch(function (reason) {
                self.error = 'Произошла ошибка: ' + reason.message;
                self.loading = false;
            })
    }
};

var router = new VueRouter({
    routes: [
        { path: '/', component: catalog },
        { path: '/authors/', component: authors },
        { path: '/publishers/', component: publishers },
        { path: '/authors/:id', component: authorBooks },
        { path: '/publishers/:id', component: publisherBooks}
    ]
});


new Vue({
    el: '#app',
    router: router
});