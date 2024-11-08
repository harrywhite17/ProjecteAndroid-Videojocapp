import React, { useState } from 'react';

function Newsletter() {
    return (
        <section className="py-20 bg-gradient-to-br bg-transparent backdrop-blur-3xl rounded-3xl mt-10">
            <div className="from-teal-700 via-teal-600 to-teal-700 relative font-[sans-serif]">
                <div className="max-w-2xl mx-auto text-center">
                    <h2 className="text-white text-4xl font-extrabold">Subscribe to Our Newsletter</h2>
                    <div className="my-6">
                        <p className="text-base text-gray-200">Subscribe to our newsletter and stay up to date with the
                            latest news, updates, and exclusive offers. Get valuable insights. Join our community today!</p>
                    </div>

                    <div
                        className="max-w-2xl left-0 right-0 mx-auto w-full p-5 flex items-center shadow-lg absolute -bottom-10">
                        <input type="email" placeholder="Enter your email"
                               className="w-full bg-red py-3.5 px-4 text-gray-800 text-base focus:outline-none"/>
                        <button
                            className="bg-[#a91079] hover:bg-[#a91079e2] text-white text-base font-semibold tracking-wide py-3.5 px-6 focus:outline-none">
                            Subscribe
                        </button>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Newsletter;
