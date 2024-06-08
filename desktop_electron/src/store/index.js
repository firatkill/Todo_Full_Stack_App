import { createStore } from "vuex";

import todoModule from "./Modules/todoModule";
import serverModule from "./Modules/serverModule";

const store = createStore({
  modules: {
    todo: todoModule,

    server: serverModule,
  },
});

export default store;
