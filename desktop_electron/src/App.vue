<template>
  <div class="App">
    <SideBar v-if="isAuthenticated" />
    <MainContainer v-if="isAuthenticated" />

    <div v-if="!isAuthenticated">
      <LoginContainer />
    </div>
    <AddTodoModal />
    <Spinner v-show="isLoading" />
  </div>
</template>

<script>
import SideBar from "./views/SideBar/SideBar.vue";
import MainContainer from "./views/Main/MainContainer.vue";
import LoginContainer from "./views/Login/LoginContainer.vue";
import AddTodoModal from "@/components/TodoContainer/AddTodoModal.vue";
import Spinner from "@/components/UI/Spinner.vue";
export default {
  name: "App",
  components: { SideBar, MainContainer, LoginContainer, AddTodoModal, Spinner },
  computed: {
    isAuthenticated() {
      return this.$store.state.server.isAuthenticated;
    },
    isLoading() {
      return this.$store.state.server.isLoading;
    },
  },
};
</script>

<style>
.App {
  display: flex;

  height: 100vh !important;
  width: 100vw !important;
  overflow: hidden;
}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-family: "Lato", sans-serif;
}
</style>
