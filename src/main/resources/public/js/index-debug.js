let app = new Vue({
    el: '#app',
    data: {
        firstNameData: {}
    },
    methods: {
        searchFirstName: function () {
            this.$http.get('/api/find/' + document.getElementById("firstNameInput").value).then(function (response) {
                    this.firstNameData = response.data;
                    var container = document.getElementById('result');
                    var items = [];
                    if (this.firstNameData.occurences == undefined) {
                        container.innerHTML = "<h1>Nous n'avons pas trouvé ce prénom</h1>";
                        return;
                    }
                    document.getElementById("smallerize").classList.add('smaller');

                    this.firstNameData.occurences.forEach(
                        function (occurence) {
                            items.push({x: occurence.date.substr(0, 10), y: occurence.number});
                        }
                    );
                    var items2 = _.chain(items)
                        .groupBy('x')
                        .map(function (value, key) {
                            return _.reduce(value, function (result, currentObject) {
                                return {
                                    x: key,
                                    y: result.y + currentObject.y
                                }
                            }, {
                                y: 0
                            });
                        })
                        .value();

                    container.innerHTML = "";
                    var dataset = new vis.DataSet(items2);
                    var options = {
                            interpolation: {
                                enabled: true,
                                parametrization: 'centripetal'
                            }
                        }
                    ;
                    new vis.Graph2d(container, dataset, options);
                }
            );
        }
    }
})