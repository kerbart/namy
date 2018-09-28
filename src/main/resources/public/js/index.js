let app = new Vue({
    el: '#app',
    data: {
        firstNamesData: {}
    },
    methods: {
        randomFirstNames: function () {
            this.$http.get('/api/random/').then(function (response) {
                    this.firstNamesData = response.data;
                    console.log("Found firstNames", this.firstNamesData);
                }
            );
        }
    },
    created: function () {
        this.randomFirstNames();
    }
})