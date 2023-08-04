/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** lib.js
*/

function trueFlatten(...data) {
    return data.flat(2147483647).map(x => ["Object"].includes(x.constructor.name) ? trueFlatten(Object.entries(x)) : x).flat(2147483647)
}

function forceArray(...data) {
    let array = [];
    data.forEach(anything => {
        if (anything.constructor.prototype[Symbol.iterator])
            array = [...array, ...anything]
        else if (anything.hasOwnProperty("entries"))
            array = [...array, ...this.forceArray(anything.entries())]
        else
            array = [...array, ...Object.entries(anything)]
    })
    return array
}

class Hash {
    constructor (...data) {
        let key = null
        let [objects, datas] = data.flat(2147483647).reduce((o, x) => {(x.constructor.name === "Object" || x.constructor.name === "Hash") ? o[0] = [...o[0], x] : o[1] = [...o[1], x]; return o}, [[],[]])

        console.log(objects, datas)
        trueFlatten(datas).forEach((x, i) => {
            if (i % 2 == 0) {
                key = x
            } else {
                if (this[key]) this[key] = x
                else Object.defineProperty(this, key, {value: x, writable: true, enumerable: true})
                key = null
            }
        })
        objects.forEach(obj => {
            Object.entries(obj).forEach(([_k, _v]) => {
                console.log("dbg", obj, _k, _v);
                if (_v && _v.constructor.name === "Object") _v = new Hash(_v)
                if (this[_k]) this[_k] = _v
                else Object.defineProperty(this, _k, {value: _v, writable: true, enumerable: true})
            })
        })
        if (key)
            Object.defineProperty(this, key, {value: null})

        Object.defineProperty(this, "length", {value: Object.entries(this).length})

        this.constructor.prototype[Symbol.iterator] = function aze() {
            let i = 0
            let elements = Object.entries(this)
            let done = false
    
            return {next() {
                let value = elements[i];
                if (!value)
                    done = true;
                i += 1;
                return {value, done}
            }}
        }
    }

    forEach(callback) {
        return Object.entries(this).forEach(callback);
    }
}

function createTag(tagName, attributes={}, children=null) {
    let tag = document.createElement(tagName);

    Object.entries(attributes).forEach(([attribute, value]) => {
        tag.setAttribute(attribute, value)
    });
    if (children) {
        if (children.constructor.name === "String") tag.innerHTML = children;
        if (children.constructor.name === "Array") children.forEach(child => { tag.appendChild(child); });
        if (children.constructor.name.match(/^HTML.*Element$/)) tag.appendChild(children);
    }
    return tag
}

const Cookie = new Proxy({}, {
    get(target, key, receiver) {
        if (document.cookie.length == 0) return undefined;
        let value = (new Map(decodeURIComponent(document.cookie).split("; ").map(pair => pair.split("=")))).get(key);
        if (typeof value === "undefined" || value === "undefined") return undefined;
        return value
    },
    set(target, key, value, receiver) {
        if (typeof value === "undefined") return document.cookie = `${key}=;expires=${(new Date((new Date()).getTime()-1*24*60*60*1000)).toUTCString()};path=/`
        return document.cookie = `${key}=${value};expires=${(new Date((new Date()).getTime()+1*24*60*60*1000)).toUTCString()};path=/`;
    }
})

function map(sourceIterable, mappingFunction) {
    let iterable = forceArray(sourceIterable);

    return iterable.map(mappingFunction)
}

function idify(string) {
    return "_"+string.replace(" ", "_").toLowerCase();
}

function pretty(string) {
    return string.toLowerCase().split(/[_-]/).map(word => word[0].toUpperCase() + word.slice(1)).join(" ");
}

export default {
    idify,
    Cookie,
    Hash,
    trueFlatten,
    createTag,
    map,
    pretty
}
