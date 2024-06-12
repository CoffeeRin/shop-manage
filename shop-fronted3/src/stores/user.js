import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useUserStore = defineStore('user', () => {
    const token = ref("")
    function setToken(tk) {
        token.value = tk
    }
    function clearToken() {
        token.value = ""
    }

    return {
        token, setToken, clearToken
    }
},
    {
        persist: {
            enabled: true,
        }
    });
