<template>
  <div class="LoginComponent">
    <form @submit="login" className="loginPageContainer">
      <h1>Log in</h1>
      <p>Log in with your e-mail and password</p>
      <hr />
      <label for="email">E-mail</label>
      <input v-model="email" name="email" type="email" />
      <label for="password">Password</label>
      <input v-model="password" name="password" type="password" />
      <div className="formButtons">
        <button type="submit">Log in</button>
      </div>
      <small @click="$emit('switchPage')" class="formSmall"
        >Don't you have an acoount? Sign Up here</small
      >
    </form>
  </div>
</template>

<script>
export default {
  name: "LoginComponent",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  computed: {},
  props: {},
  methods: {
    login(e) {
      e.preventDefault();
      const user = { email: this.email, password: this.password };
      this.$store.dispatch("loginUser", user);
    },
  },
};
</script>

<style scoped>
.loginPageContainer {
  display: flex;
  border: 3px solid hsl(236, 33%, 92%);
  flex-direction: column;
  padding: 2vw 2vw;

  border-radius: 1vw;
}
@media screen and (max-width: 768px) {
  .loginPageContainer h1 {
    font-size: 7.5vw !important;
  }
  .loginPageContainer p {
    font-size: 4vw !important;
  }
  .loginPageContainer label {
    font-size: 4vw !important;
  }
  .loginPageContainer input {
    font-size: 5vw !important;
    height: 8vw !important;
  }
  .loginPageContainer button {
    font-size: 5vw !important;
  }
  .loginPageContainer a {
    font-size: 3vw !important;
    margin-top: 2vw !important;
  }
}
.loginPageContainer {
  background-color: white;
}

.loginPageContainer h1 {
  color: hsl(235, 19%, 35%);
}
.loginPageContainer h1 {
  font-size: 3vw;
}

.loginPageContainer p {
  color: hsl(236, 33%, 80%);
}
.loginPageContainer p {
  margin-top: 0.5vw;
  margin-bottom: 0.5vw;
  font-size: 2vw;
  font-weight: bold;
}

.loginPageContainer hr {
  margin-bottom: 1vw;
}

.loginPageContainer label {
  color: hsl(235, 19%, 35%);
}
.loginPageContainer label {
  font-size: 2vw;
  margin-bottom: 0.3vw;
  margin-top: 1vw;
}

.loginPageContainer input {
  color: hsl(235, 19%, 35%);
  background-color: hsl(236, 33%, 92%);
}
.loginPageContainer input {
  font-size: 2.5vw;
  height: 4.5vw;
  padding-left: 0.5vw;
  font-weight: bold;
  border-radius: 1vw;
  border: none;
  outline: none;
}

.loginPageContainer input:focus {
  outline: 0.2vw solid hsl(235, 19%, 35%);
}

.formButtons {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 2vw;
}

.loginPageContainer .formButtons button {
  color: hsl(235, 19%, 35%);
  border: 0.2vw solid hsl(235, 19%, 35%);
}
.formButtons button {
  background-color: transparent;
  font-size: 2vw;
  border-radius: 1vw;
  padding: 1vw 2vw;
  cursor: pointer;
  display: flex;

  margin: auto;
  margin-bottom: 1vw;
  font-weight: bold;
}
.formButtons button .icon {
  margin-left: 1vw;
}

.loginPageContainer .formButtons button:hover {
  background-color: hsl(235, 19%, 35%);
  color: hsl(236, 33%, 92%);
}

.formSmall {
  align-self: center;
  margin-top: 1vw;
  font-size: 2vw;
  cursor: pointer;
}

.formSmall:hover {
  color: rgb(72, 72, 255) !important;
}
</style>
