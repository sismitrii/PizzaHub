/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** lib.js
*/

class _Cookie {
    set(_key, _value, validityInDays=7, path="/") {
        const today = new Date();
        const expirationDay = new Date(today.getTime() + validityInDays * 24 * 60 * 60 * 1000);

        document.cookie = `${_key}=${_value};expires=${expirationDay.toUTCString()};path=${path}`;
    }

    static getAll() {
        if (document.cookie.length == 0) return new Map();
        return new Map(decodeURIComponent(document.cookie).split("; ").map(pair => pair.split("=")));
    }

    get(_key) { return this.getCookies().get(_key); }
    del(_key, path="/") { this.setCookie(_key, "", -1, path); }
}

export default {
    createTag(tagName, attributes={}, children=null) {
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
    },

    idify(string) {
        return "_"+string.replace(" ", "_").toLowerCase();
    },

    Cookie: new Proxy({}, {
        get(target, key, receiver) {
            if (document.cookie.length == 0) return undefined;
            if (["delete", "unset", "remove", "clear"].includes(key)) return (_key) => document.cookie = `${_key}=;expires=${(new Date((new Date()).getTime()-1*24*60*60*1000)).toUTCString()};path=/`;
            return (new Map(decodeURIComponent(document.cookie).split("; ").map(pair => pair.split("=")))).get(key);
        },
        set(target, key, value, receiver) {
            return document.cookie = `${key}=${value};expires=${(new Date((new Date()).getTime()+1*24*60*60*1000)).toUTCString()};path=/`;
        }
    }),

    forceArray(...data) {
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
    },

    objectify(any, ...data) {
        return new Map(this.forceArray(any, ...data))
    },

    map(sourceIterable, mappingFunction) {
        let iterable = this.forceArray(sourceIterable);

        return iterable.map(mappingFunction)
    }
}
