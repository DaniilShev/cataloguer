Vue.component('book-list', {
    props: {
        books: {
            type: Array,
            required: true
        },
        pageCount: {
            type: Number,
            default: 1
        },
        currentPage: {
            type: Number,
            default: 1
        }
    },
    methods: {
        go: function(number) {
            this.$emit('go', number);
        }
    }
});

Vue.component('catalog', {
    data: function() {
        return {
            query: '',
            books: [],
            pageCount: 0,
            error: ''
        };
    },
    created: function() {
        var self = this;
        fetch('/books')
            .then(function (response) {
                return response.json();
            })
            .then(function (json) {
                self.books = json.books
            })
            .catch(function (reason) {
                self.error = reason;
            })
    },
    methods: {
        search: function() {
            var self = this;
            fetch('/books/search?q=' + this.query)
                .then(function (response) {
                    return response.json();
                })
                .then(function(json) {
                    self.books = json.books
                })
                .catch(function (reason) {
                    self.error = reason;
                })
        }
    },
    watch: {
        query: _.debounce(
            function() {
                this.search();
            },
            500
        )
    }
});

Vue.component('authors-books', {
    props: {
        authorId: {
            type: Number,
            required: true
        }
    },
    data: function() {
        return {
            author: {},
            books: [],
            pageCount: 0,
            error: ''
        };
    },
    created: function() {
        var self = this;
        fetch('/authors/' + this.authorId + '/books')
            .then(function (response) {
                return response.json();
            })
            .then(function(json) {
                self.books = json.books
            })
            .catch(function (reason) {
                self.error = reason;
            })
    }
});

Vue.component('publisher-books', {
    props: {
        publisherId: {
            type: Number,
            required: true
        }
    },
    data: function() {
        return {
            publisher: {},
            books: [],
            pageCount: 0,
            error: ''
        };
    },
    created: function() {
        var self = this;
        fetch('/publishers/' + this.publisherId + '/books')
            .then(function (response) {
                return response.json();
            })
            .then(function(json) {
                self.books = json.books
            })
            .catch(function (reason) {
                self.error = reason;
            })
    }
});



new Vue({
    el: '#app',
    data: {
        window: 'catalog'
    }
});