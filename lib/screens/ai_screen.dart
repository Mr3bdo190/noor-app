import 'package:flutter/material.dart';

class AIScreen extends StatefulWidget {
  const AIScreen({super.key});

  @override
  State<AIScreen> createState() => _AIScreenState();
}

class _AIScreenState extends State<AIScreen> {
  final TextEditingController _controller = TextEditingController();
  String _response = "أهلاً بك.. أخبرني بماذا تشعر الآن؟";

  void _getReflection() {
    setState(() {
      if (_controller.text.contains("حزين") || _controller.text.contains("تعبان")) {
        _response = "﴿وَبَشِّرِ الصَّابِرِينَ﴾.. إن مع العسر يسراً.";
      } else if (_controller.text.contains("خايف")) {
        _response = "﴿لَا تَحْزَنْ إِنَّ اللَّهَ مَعَنَا﴾.. أنت في حفظ الله.";
      } else {
        _response = "﴿فَاذْكُرُونِي أَذْكُرْكُمْ﴾.. اجعل ذكر الله أنيسك.";
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(20),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Icon(Icons.psychology, size: 80, color: Color(0xFF38BDF8)),
          const SizedBox(height: 20),
          Text(_response, style: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold), textAlign: TextAlign.center),
          const SizedBox(height: 30),
          TextField(
            controller: _controller,
            decoration: InputDecoration(
              hintText: "أنا أشعر بـ...",
              filled: true,
              fillColor: const Color(0xFF1E293B),
              border: OutlineInputBorder(borderRadius: BorderRadius.circular(15)),
            ),
          ),
          const SizedBox(height: 20),
          ElevatedButton(
            onPressed: _getReflection,
            style: ElevatedButton.styleFrom(backgroundColor: const Color(0xFF38BDF8)),
            child: const Text("تدبر حالي", style: TextStyle(color: Colors.black)),
          ),
        ],
      ),
    );
  }
}
