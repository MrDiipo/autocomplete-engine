(function (window, document){

    function setUp(q){
        const wrapper = document.createElement('span');

        const qq = q.cloneNode(true); // deep clone of q
        wrapper.appendChild(qq);

        const r = document.createElement('div');
        wrapper.appendChild(r);

        q.parentElement.insertBefore(wrapper, q);
        q.parentNode.removeChild(q);


        function displayResults(results){
            if (results.length === 0){
                r.style.display = 'none';
                return;
            }

            let i, len;
            const ps = r.getElementsByTagName('p');
            for (i = ps.length -1; i >= 0; i--){
                ps[i].remove();
            }

            r.style.display = 'block';
            len=results.length;
            for (i=0;  i < len; i ++){
                let p = document.createElement('p');
                p.innerHTML = results[i][1];
                r.appendChild(p);
            }
        }


        function handle() {
            console.log("Handler called. q.value: "+ qq.value + "'");

            const req = new window.XMLHttpRequest();
            req.onreadystatechange = function (){
                if (req.readyState === 4) {// DONE, the operation is complete

                    if (req.status == 200){
                        const response = JSON.parse(req.responseText);
                        displayResults(response.results);
                    } else {
                        if (req.status !== 0){
                            console.warn(req.status, req.responseText);
                            r.style.display = 'none';
                        }
                    }

                     }
            };

            const url = location.protocol + '//' + location.host + '/autocomplete' + '?q=';
            req.open('GET', url + encodeURIComponent(qq.value.trim()), true);
            req.send();
            console.log("Requested autocomplete for  '" + qq.value.trim() + "'")
        }
    }
    }
)