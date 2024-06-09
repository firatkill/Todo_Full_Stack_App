const serverModule = {
  state() {
    return {
      access_token: "",
      username: "",
      isAuthenticated: true,
      isLoading: false,
    };
  },
  getters: {
    getUsername() {
      return JSON.parse(sessionStorage.getItem("username"));
    },
    getAccessToken() {
      if (sessionStorage.getItem("access_token")) {
        return JSON.parse(sessionStorage.getItem("access_token"));
      } else {
        return null;
      }
    },
  },

  mutations: {
    toggleLoading(state, payload) {
      state.isLoading = payload;
    },
    login(state, payload) {
      state.isAuthenticated = true;
      sessionStorage.setItem(
        "access_token",
        JSON.stringify(payload.access_token)
      );
      sessionStorage.setItem("username", JSON.stringify(payload.username));
    },
    updateToken(state, access_token) {
      sessionStorage.setItem("access_token", JSON.stringify(access_token));
      state.isAuthenticated = true;
    },
    logout(state) {
      state.isLoading = true;
      state.isAuthenticated = false;
      sessionStorage.removeItem("access_token");
      sessionStorage.removeItem("username");
      state.isLoading = false;
    },
  },
  actions: {
    logout({ commit }) {
      commit("logout");
    },
    async changePassword({ commit, getters }, body) {
      const access_token = getters.getAccessToken;
      if (access_token) {
        commit("toggleLoading", true);
        let response = await fetch(
          `http://localhost:8082/api/auth/changepassword`,
          {
            method: "PUT",

            headers: {
              Authorization: getters.getAccessToken,
              "Content-Type": "application/json",
            },
            body: JSON.stringify(body),
          }
        );
        commit("toggleLoading", false);

        if (response.ok) {
          await commit("updateToken", response.headers.get("access_token"));
          alert("Password updated successfully");
          return true;
        } else {
          return false;
        }
      } else {
        commit("logout");
      }
    },
    async deleteMultipleTodos({ dispatch, commit, getters }, todoIds) {
      const access_token = getters.getAccessToken;
      if (access_token) {
        commit("toggleLoading", true);

        for (let i = 0; i < todoIds.length; i++) {
          const id = todoIds[i];
          await fetch(`http://localhost:8082/api/user/todos/${id}`, {
            method: "DELETE",

            headers: {
              Authorization: getters.getAccessToken,
              "Content-Type": "application/json",
            },
          }).catch(() => {
            commit("logout");
          });
        }
        commit("toggleLoading", false);

        await dispatch("getAllTodos");
      } else {
        commit("logout");
      }
    },
    async deleteTodo({ dispatch, commit, getters }, todoId) {
      const access_token = getters.getAccessToken;
      if (access_token) {
        commit("toggleLoading", true);
        let response = await fetch(
          `http://localhost:8082/api/user/todos/${todoId}`,
          {
            method: "DELETE",

            headers: {
              Authorization: getters.getAccessToken,
              "Content-Type": "application/json",
            },
          }
        ).catch(() => {
          commit("logout");
        });
        commit("toggleLoading", false);

        await commit("updateToken", response.headers.get("access_token"));

        await dispatch("getAllTodos");
      } else {
        commit("logout");
      }
    },
    async updateTodo({ dispatch, commit, getters }, todo) {
      let todoToUpdate = todo;
      todoToUpdate.completed = !todoToUpdate.completed;
      todoToUpdate.todoCategory = todo.todoCategory.categoryName;

      const access_token = getters.getAccessToken;
      if (access_token) {
        commit("toggleLoading", true);
        let response = await fetch(
          `http://localhost:8082/api/user/todos/${todoToUpdate.id}`,
          {
            method: "PUT",

            headers: {
              Authorization: getters.getAccessToken,
              "Content-Type": "application/json",
            },
            body: JSON.stringify(todoToUpdate),
          }
        ).catch(() => {
          commit("logout");
        });
        commit("toggleLoading", false);
        await commit("updateToken", response.headers.get("access_token"));

        await dispatch("getAllTodos");
      } else {
        commit("logout");
      }
    },
    async postTodoCategory({ commit, getters, dispatch }, category) {
      const access_token = getters.getAccessToken;

      if (access_token) {
        commit("toggleLoading", true);
        let response = await fetch(
          "http://localhost:8082/api/user/todocategories",
          {
            method: "POST",

            headers: {
              Authorization: access_token,
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              categoryName: category,
            }),
          }
        ).catch(() => commit("logout"));
        commit("toggleLoading", false);
        await commit("updateToken", response.headers.get("access_token"));

        await dispatch("getAllCategories");
      } else {
        commit("logout");
      }
    },
    async postTodo({ dispatch, commit, getters }, todo) {
      const access_token = getters.getAccessToken;
      if (access_token) {
        commit("toggleLoading", true);
        let response = await fetch("http://localhost:8082/api/user/todos", {
          method: "POST",

          headers: {
            Authorization: getters.getAccessToken,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(todo),
        }).catch(() => {
          commit("logout");
        });
        commit("toggleLoading", false);
        await commit("updateToken", response.headers.get("access_token"));

        await dispatch("getAllTodos");
        commit("toggleModal", false, { root: true });
      } else {
        commit("logout");
      }
    },
    async getAllCategories({ commit, getters }) {
      const access_token = getters.getAccessToken;

      if (access_token) {
        commit("toggleLoading", true);
        let response = await fetch(
          "http://localhost:8082/api/user/todocategories",
          {
            method: "GET",

            headers: {
              Authorization: getters.getAccessToken,
              "Content-Type": "application/json",
            },
          }
        ).catch(() => {
          commit("logout");
        });
        commit("toggleLoading", false);
        if (response.ok) {
          await commit("updateToken", response.headers.get("access_token"));
          response = await response.json();
          await commit("setCategories", response);
        } else {
          commit("logout");
        }
      } else {
        commit("logout");
      }
    },
    async getAllTodos({ commit, getters }) {
      const access_token = getters.getAccessToken;

      if (access_token) {
        commit("toggleLoading", true);
        let response = await fetch("http://localhost:8082/api/user/todos", {
          method: "GET",

          headers: {
            Authorization: getters.getAccessToken,
            "Content-Type": "application/json",
          },
        }).catch(() => {
          commit("logout");
        });
        commit("toggleLoading", false);

        if (response.ok) {
          commit("updateToken", response.headers.get("access_token"));
          response = await response.json();
          commit("setTodos", response);
        } else {
          commit("logout");
        }
      } else {
        commit("logout");
      }
    },
    async signupUser(context, state, user) {
      context.commit("toggleLoading", true);
      let response = await fetch("http://localhost:8082/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },

        body: JSON.stringify({
          username: user.username,
          email: user.email,
          password: user.password,
        }),
      });
      context.commit("toggleLoading", false);
      if (response.ok) {
        alert("User successfully registered.");
      } else {
        response = await response.json();
        alert(response);
      }
    },
    async loginUser(context, user) {
      context.commit("toggleLoading", true);
      await fetch("http://localhost:8082/api/auth/login", {
        method: "POST",

        body: JSON.stringify(user),
      })
        .then((resp) => resp.json())
        .then((resp) => {
          context.commit("login", {
            username: resp.username,
            access_token: resp.access_token,
          });
        })
        .then(() => {
          context.dispatch("getAllCategories");
          context.dispatch("getAllTodos");
        });
      context.commit("toggleLoading", false);
    },
  },
};

export default serverModule;
