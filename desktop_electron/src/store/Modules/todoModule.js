const todoModule = {
  state() {
    return {
      todos: [],
      currentCategory: [{ id: 0, categoryName: "All" }],
      categories: [],
      currentTodos: [],
      isAddTodoModal: false,
    };
  },
  getters: {
    getTodosByCategory(state) {
      if (state.currentCategory.categoryName !== "All") {
        return state.todos.filter(
          (todo) => todo.todoCategory.id === state.currentCategory.id
        );
      } else {
        return state.todos;
      }
    },
  },
  mutations: {
    toggleModal(state, payload) {
      state.isAddTodoModal = payload;
    },
    checkTodo(state, id) {
      const todo = state.currentTodos.find((todo) => todo.id === id);
      todo.isCompleted = !todo.isCompleted;
    },
    deleteTodo(state, id) {
      const todo = state.todos.find((todo) => todo.id === id);
      state.todos.splice(state.todos.indexOf(todo), 1);
      state.currentTodos.splice(state.currentTodos.indexOf(todo), 1);
    },
    addTodo(state, todo) {
      state.todos.push({ id: state.todos.length + 1, ...todo });
      state.currentTodos = state.todos.filter(
        (temptodo) => temptodo.category === todo.category
      );
    },
    changeCategory(state, category) {
      if (category.categoryName !== "All") {
        state.currentTodos = state.todos.filter(
          (todo) => todo.todoCategory.id === category.id
        );
        state.currentCategory = category;
      } else {
        state.currentCategory = { id: 0, categoryName: "All" };
        state.currentTodos = state.todos;
      }
    },
    setTodos(state, todos) {
      state.todos = todos;
      state.currentTodos = todos;
    },
    setCategories(state, categories) {
      state.categories = [{ id: 0, categoryName: "All" }, ...categories];
    },
  },
  actions: {
    toggleModal(context, payload) {
      context.commit("toggleModal", payload);
    },
    deleteTodo(context, todo) {
      if (context.state.currentTodos.length == 1) {
        context.commit("deleteTodo", todo.id);
        context.commit("changeCategory", "All");
      } else {
        context.commit("deleteTodo", todo.id);
      }
    },
    checkTodo(context, todo) {
      context.commit("checkTodo", todo.id);
    },
    addTodo(context, todo) {
      context.commit("addTodo", todo);
    },
    changeCategory(context, category) {
      context.commit("changeCategory", category);
    },
  },
};

export default todoModule;
