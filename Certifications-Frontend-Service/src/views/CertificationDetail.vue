<template>
  <div>
    <!-- component -->
    <main
      class="grid place-items-center min-h-screen bg-gradient-to-t from-blue-200 to-indigo-900 p-5"
    >
      <div>
        <h1
          class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200 mb-5"
        >
          {{ currentCertification.name }}
        </h1>
        <div
          class="relative bg-white py-6 px-6 rounded-3xl w-86 my-4 shadow-xl"
        >
          <div
            class=" text-white flex items-center absolute rounded-full py-4 px-4 shadow-xl bg-green-500 left-4 -top-6"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24px"
              viewBox="0 0 24 24"
              width="24px"
              fill="#FFFFFF"
            >
              <path d="M0 0h24v24H0V0z" fill="none" />
              <path
                d="M8 16h8v2H8zm0-4h8v2H8zm6-10H6c-1.1 0-2 .9-2 2v16c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm4 18H6V4h7v5h5v11z"
              />
            </svg>
          </div>
          <div class="mt-8">
            <div class="flex space-x-2" v-if="certificationHasState">
              <p>
                State of this certification is <strong>{{ currentCertification.state }}!</strong>
              </p>
            </div>

            
            <div class="flex space-x-2 text-gray-400 text-md my-2">
              <!-- svg  -->

              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="24px"
                viewBox="0 0 24 24"
                width="24px"
                fill="#000000"
              >
                <path d="M0 0h24v24H0V0z" fill="none" />
                <path
                  d="M17 7h-4v2h4c1.65 0 3 1.35 3 3s-1.35 3-3 3h-4v2h4c2.76 0 5-2.24 5-5s-2.24-5-5-5zm-6 8H7c-1.65 0-3-1.35-3-3s1.35-3 3-3h4V7H7c-2.76 0-5 2.24-5 5s2.24 5 5 5h4v-2zm-3-4h8v2H8z"
                />
              </svg>

              <p> <a href="currentCertification.url">{{ currentCertification.url }}</a></p>
            </div>
            <div class="flex space-x-2 text-gray-400 text-md my-2">
              <!-- svg  -->

              <svg
                xmlns="http://www.w3.org/2000/svg"
                enable-background="new 0 0 24 24"
                height="24px"
                viewBox="0 0 24 24"
                width="24px"
                fill="#000000"
              >
                <g><rect fill="none" height="24" width="24" /></g>
                <g>
                  <g />
                  <path
                    d="M15,18.5c-2.51,0-4.68-1.42-5.76-3.5H15l1-2H8.58c-0.05-0.33-0.08-0.66-0.08-1s0.03-0.67,0.08-1H15l1-2H9.24 C10.32,6.92,12.5,5.5,15,5.5c1.61,0,3.09,0.59,4.23,1.57L21,5.3C19.41,3.87,17.3,3,15,3c-3.92,0-7.24,2.51-8.48,6H3l-1,2h4.06 C6.02,11.33,6,11.66,6,12s0.02,0.67,0.06,1H3l-1,2h4.52c1.24,3.49,4.56,6,8.48,6c2.31,0,4.41-0.87,6-2.3l-1.78-1.77 C18.09,17.91,16.62,18.5,15,18.5z"
                  />
                </g>
              </svg>
              <p>{{ currentCertification.price }} {{ currentCertification.currency }}</p>
            </div>
            <div class="border-t-2"></div>
            <div class="flex items-center justify-center h-auto p-5">
              <div class="container">
                <p>Skills learned with this certification:</p>
                <div class="flex justify-center">
                  
                  <div class="bg-white shadow-xl rounded-lg w-1/2">
                  
                    <ul class="divide-y divide-gray-300">
                      <li v-for="skill in certificationSkills" :key="skill.name" class="p-4 hover:bg-gray-50 cursor-pointer">
                        {{skill.name}}
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
            <v-btn
              color="danger"
              @click="deleteCertificationDialog = true"
            >
              Delete
            </v-btn>
          </div>
        </div>
        <v-dialog v-model="deleteCertificationDialog" persistent>
        <div class="min-w-screen h-screen animated fadeIn faster  fixed  left-0 top-0 flex justify-center items-center inset-0 z-50 outline-none focus:outline-none">
        <div class="flex flex-col p-8 bg-white shadow-md hover:shodow-lg rounded-2xl">
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <svg xmlns="http://www.w3.org/2000/svg"
                class="w-16 h-16 rounded-2xl p-3 border border-blue-100 text-blue-400 bg-blue-50" fill="none"
                viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
              </svg>
              <div class="flex flex-col ml-3">
                <div class="font-medium leading-none">Delete certification Acccount ?</div>

              </div>
            </div>
            <v-btn @click="deleteCertification" mat-icon-button="" color="danger" class="flex-no-shrink bg-red-500 px-5 ml-4 py-2 text-sm shadow-sm hover:shadow-lg font-medium tracking-wider border-2 border-red-500 text-white rounded-full">Delete</v-btn>
            <v-btn @click="deleteCertificationDialog = false"  mat-icon-button="" class="flex-no-shrink bg-red-500 px-5 ml-4 py-2 text-sm shadow-sm hover:shadow-lg font-medium tracking-wider border-2 border-red-500 text-white rounded-full">Cancel</v-btn>          
            <div v-if="true" class="flex items-center justify-center h-auto p-5">
                <p class="text-sm text-gray-600 leading-none mt-1" >
                  When you delete this certification, following voucher will be deleted as well:
                </p>              
              <div class="container">
                <div class="flex justify-center">
                  <div class="bg-white shadow-xl rounded-lg w-1/2">
                    <ul class="divide-y divide-gray-300">
                      <li v-for="voucher in certificationVouchers" :key="voucher.voucherCode" class="p-4 hover:bg-gray-50 cursor-pointer">
                        {{voucher.voucherCode}}
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        </div>
      </v-dialog>
      </div>
    </main>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "CertificationDetail",
  data() {
    return {
      currentCertification: {},
      certificationSkills: [],
      certificationVouchers: [],
      deleteCertificationDialog: false,
    };
  },
  async created() {
    this.currentCertification = this.certifications.find(
      ({ id }) => id == this.$route.params.id
    );
  },
  async mounted() {
    await this.fetchSkills();
    await this.fetchVouchers();
  },
  computed: {
    ...mapGetters(["certifications"]),
    certificationHasState() {
      return this.currentCertification.state != null;
    },
    hasAffectedVouchers() {
      return this.currentCertification.certificationVouchers != null && 
            this.currentCertification.certificationVouchers.length != 0;
    }
  },
  methods: {
    async deleteCertification() {
      if (Object.keys(this.currentCertification) === 0) {
        return;
      }
      const token = JSON.parse(localStorage.getItem("token"));
      const url = "http://localhost:8080/delete-certification/" + this.currentCertification.id
      try {
        const { data } =  await axios.delete(url, {
          headers: {
            Authorization: 'Bearer ' + token 
          }
        });
        this.currentCertification = {};
        this.$router.push("/certificationsoverview");
      } catch (error) {
        console.log(error);
      }
    },
    async fetchSkills() {
      const token = JSON.parse(localStorage.getItem("token"));
      const url = "http://localhost:8080/certification-skills/" + this.currentCertification.id;
      try {
        const { data } = await axios.get(url, {
          headers: {
            Authorization: 'Bearer ' + token 
          }
        });
        this.certificationSkills = data;
      } catch (error) {
        console.log(error);
      }
    },

    async fetchVouchers() {
      const token = JSON.parse(localStorage.getItem("token"));
      const url = "http://localhost:8080/certification-vouchers/" + this.currentCertification.id;
      try {
        const { data } = await axios.get(url, {
          headers: {
            Authorization: 'Bearer ' + token 
          }
        });          
        this.certificationVouchers =  data;
        console.log(data);
        if (this.hasAffectedVouchers) {
            console.log("has vouchers");
        }
      } catch (error) {
        console.log(error);
      }
    }
  },
};
</script>

<style>

</style>
